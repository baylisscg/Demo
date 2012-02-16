require 'ant'

namespace :ivy do

  ivy_install_version = '2.2.0'
  ivy_jar_dir = './ivy'
	ivy_jar_file = "#{ivy_jar_dir}/ivy.jar"

  task :download do
    mkdir_p ivy_jar_dir
		ant.get :src => "http://repo1.maven.org/maven2/org/apache/ivy/ivy/#{ivy_install_version}/ivy-#{ivy_install_version}.jar",
      :dest => ivy_jar_file,
      :usetimestamp => true
  end

	task :install => :download do
		ant.path :id => 'ivy.lib.path' do
			fileset :dir => ivy_jar_dir, :includes => '*.jar'
		end
		ant.taskdef :resource => "org/apache/ivy/ant/antlib.xml", :classpathref => "ivy.lib.path"
  end

	task :configure => :install do
#		ant.properties.each { |key,value| print key+" : "+value+"\n" }
		ant.property :name=>"ivy.settings.dir", :value=>"./"
		ant.configure :file=> "${ivy.settings.dir}/ivysettings.xml"
	end

end

SOURCE_DIR="src/main/java"
CLASSES_DIR="build"

task :compile => "ivy:configure" do

	ant.retrieve :conf=>"core"
	ant.cachepath :pathid=>"ivy.build.classpath", :conf=>"core"

  ant.mkdir :dir => CLASSES_DIR
  ant.javac( :srcdir => SOURCE_DIR,
             :destdir => CLASSES_DIR,
             :source => "1.6",
             :target => "1.6",
             :debug => "yes",
             :includeantruntime => "no" ) do
		classpath :refid => 'ivy.build.classpath'
	end
end

task :package => :compile do
	ant.jar :destfile=>"test.jar",:basedir=>CLASSES_DIR
end

task :run => :compile do

	ant.path :id=> "classes" do
		ant.cachepath :pathid=>"ivy.build.classpath", :conf=>"runtime"
		path :refid=>"ivy.build.classpath"
		pathelement :location => CLASSES_DIR
	end

	ant.java :classpathref=>"classes", :classname=>"au.org.aurin.test.async.Ping"

end

namespace :jbehave do

	task :install => "ivy:configure" do
		ant.retrieve :conf=>"test"
		ant.cachepath :pathid=>"ivy.test.classpath", :conf=>"test"
		ant.taskdef :name=>"runStoriesAsEmbeddables", :classpathref => "ivy.test.classpath", :classname=>"org.jbehave.ant.RunStoriesAsEmbeddables"
	end

end


task :test => [:compile,"jbehave:install"] do
	ant.cachepath :pathid=>"ivy.test.classpath", :conf=>"test"
	ant.runStoriesAsEmbeddables :includes=>"**/*Stories.java",
	      :metaFilters=>"+author *,-skip",
	      :systemProperties=>"java.awt.headless=true",
	      :ignoreFailureInStories=>"true",
        :ignoreFailureInView=>"false",
        :generateViewAfterStories=>"true"
end
