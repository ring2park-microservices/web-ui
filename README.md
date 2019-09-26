Ring2Park Online Micro Service (Web UI)
=======================================

This is a repository for a component of the demo/reference application "Ring2Park Online" built using a micro service architecture.
This is the main web user interface.

Build Instructions
------------------

`mvnw clean package`

Run Instructions
----------------

In development mode:

`mvnw spring-boot:run`

In test\production mode:

```
docker build -t web-ui:1.0.X . 
docker run -d --name web-ui web-ui:1.0.x
```

If you have any comments or suggestions then please contact me at [kevin.lee@microfocus.com](mailto:kevin.lee@microfocus.com).

Kevin Lee
