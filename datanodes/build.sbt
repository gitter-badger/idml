name := "idml-datanodes"

libraryDependencies ++= Seq(
  "joda-time"        % "joda-time"       % "2.9.9", // FIXME: Create date and time plugin
  "org.joda"         % "joda-convert"    % "1.7", // FIXME: Create date and time plugin
  "javax.mail"       % "mail"            % "1.5.0-b01", // FIXME: Create email plugin
  "org.json4s"       %% "json4s-jackson" % "3.6.3",
  "com.google.guava" % "guava"           % "27.0-jre",
  "org.typelevel"    %% "spire"          % "0.16.0",
  "org.typelevel"    %% "cats-core"      % "1.5.0",
  "com.google.re2j"  % "re2j"            % "1.2",
  "org.scalatest"    %% "scalatest"      % "3.0.4" % Test
)
