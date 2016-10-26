(defproject helloapp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  
  :dependencies [[org.clojure/clojure "1.8.0"]
  				 [org.clojure/data.json "0.2.6"]
                 [com.amazonaws/aws-lambda-java-core "1.1.0"]
                 [com.amazonaws/aws-lambda-java-events "1.3.0"]
                 [org.taoclj/foundation "0.1.3"]]

  :test-paths ["src"]
  :main helloapp)
