(defproject projecteuler "0.1.0-SNAPSHOT"
  :description "project euler in clojure"
  :url "https://github.com/enricod/projecteuler"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main ^:skip-aot eulerclj.p022
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
