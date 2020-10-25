val closureCompilerVersion = "v20201006"

plugins {
  java
}

dependencies {
  runtimeOnly("com.google.javascript:closure-compiler:$closureCompilerVersion")
}

repositories {
  jcenter()
}

task("minify", JavaExec::class) {
  group = "minify"
  main = "com.google.javascript.jscomp.CommandLineRunner"
  args = listOf(
      "--compilation_level", "ADVANCED",
      "--js", "my-lib.externs.js",
      "--js", "my-lib.js",
      "--js_output_file", "my-lib.min.js",
      "--create_source_map", "my-lib.min.js.map",
      // workaround - add namespace to the output
      "--output_wrapper", "const myLib={};(function(){%output%})()\n//# sourceMappingURL=my-lib.min.js.map"
  )
  classpath = sourceSets["main"].runtimeClasspath
}
