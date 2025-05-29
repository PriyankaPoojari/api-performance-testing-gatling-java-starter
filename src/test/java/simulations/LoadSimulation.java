package simulations;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import common.ProjectInfo;

public class LoadSimulation extends Simulation{
    HttpProtocolBuilder httpProtocol = http
            .baseUrl(ProjectInfo.BASE_URL)
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    ScenarioBuilder scn = scenario("REST API Test Scenario")
            .exec(
                    http("Get All Employees")
                            .get("/api/v1/employees")
                            .check(status().is(200))
            )
            .pause(5)
            .exec(
                    http("Get Employee by ID")
                            .get("/api/v1/employee/1")
                            .check(status().is(200))
                            .check(jsonPath("$.id").is("1"))
            )
            .pause(5)
            .exec(
                    http("Create Post")
                            .post("/create")
                            .body(StringBody("{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}")).asJson()
                            .check(status().is(200))
            ).pause(5)
            .exec(
                    http("Update Id")
                            .put("/update/2")
                            .body(StringBody("{\"name\":\"test\",\"salary\":\"50000\",\"age\":\"56\"}")).asJson()
                            .check(status().is(200))
            ).pause(5)
            .exec(
                    http("Delete Id")
                            .delete("/delete/719")
                            .check(status().is(200))
            );


    {
        setUp(
                scn.injectOpen(
                        rampUsers(ProjectInfo.USER_COUNT).during(ProjectInfo.RAMP_DURATION)
                ).protocols(httpProtocol)
        ).maxDuration(ProjectInfo.TEST_DURATION);
    }
}

