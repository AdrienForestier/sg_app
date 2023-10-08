Sample Spark App exposing over Http, csv Database: https://docs.aws.amazon.com/redshift/latest/gsg/rs-gsg-create-sample-db.html

To run, use jvm parameters:
```
-Xmx3g
--add-opens=java.base/java.lang=ALL-UNNAMED
--add-opens=java.base/java.lang.invoke=ALL-UNNAMED
--add-opens=java.base/java.lang.reflect=ALL-UNNAMED
--add-opens=java.base/java.io=ALL-UNNAMED
--add-opens=java.base/java.net=ALL-UNNAMED
--add-opens=java.base/java.nio=ALL-UNNAMED
--add-opens=java.base/java.util=ALL-UNNAMED
--add-opens=java.base/java.util.concurrent=ALL-UNNAMED
--add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED
--add-opens=java.base/sun.nio.ch=ALL-UNNAMED
--add-opens=java.base/sun.nio.cs=ALL-UNNAMED
--add-opens=java.base/sun.security.action=ALL-UNNAMED
--add-opens=java.base/sun.util.calendar=ALL-UNNAMED
--add-opens=java.security.jgss/sun.security.krb5=ALL-UNNAMED 
```

When the application starts, it is logged:
```
users count:49990
event count:8798
category count:11
date2008 count:365
venue count:202
listings count:192497
sales count:172456
```


# use Swagger OpenApi 3:

open http://localhost:8080/swagger-ui.html

notice it will redirect you to page
http://localhost:8080/swagger-ui/index.html#/
and also fill "/v3/api-docs"


# Example of generic query:

```
http://localhost:8080/swagger-ui/index.html#/Sales%20Rest/query
{
    "limit": 10,
    "exprs": [
        "salesId", "listId"
    ]
}
```

Result:
```
[
  {
    "cols": [
      1,
      1
    ]
  },
  {
    "cols": [
      2,
      4
    ]
  },
  ...
```