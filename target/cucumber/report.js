$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("BDLT_Web_Service.feature");
formatter.feature({
  "line": 1,
  "name": "Response of a web-page",
  "description": "",
  "id": "response-of-a-web-page",
  "keyword": "Feature"
});
formatter.before({
  "duration": 994190085,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "sending HTTP request to https://www.iste.uni-stuttgart.de/rss/",
  "description": "",
  "id": "response-of-a-web-page;sending-http-request-to-https://www.iste.uni-stuttgart.de/rss/",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "the next beginning of Summer Term",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "\"run\" the experiment for 24 \"hours\"",
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "\"ensure\" a \"positive answer\" should return",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "\"ensure\" a \"response time\" is \"less\" than 1000 \"miliseconds\"",
  "keyword": "And "
});
formatter.match({
  "location": "WebServiceStepDefinition.the_next_beginning_of_Summer_Term()"
});
formatter.result({
  "duration": 115190089,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "run",
      "offset": 1
    },
    {
      "val": "24",
      "offset": 25
    },
    {
      "val": "hours",
      "offset": 29
    }
  ],
  "location": "WebServiceStepDefinition.the_experiment_for(String,int,String)"
});
formatter.result({
  "duration": 2906451,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "ensure",
      "offset": 1
    },
    {
      "val": "positive answer",
      "offset": 12
    }
  ],
  "location": "WebServiceStepDefinition.a_should_return(String,String)"
});
formatter.result({
  "duration": 3639036,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "ensure",
      "offset": 1
    },
    {
      "val": "response time",
      "offset": 12
    },
    {
      "val": "less",
      "offset": 31
    },
    {
      "val": "1000",
      "offset": 42
    },
    {
      "val": "miliseconds",
      "offset": 48
    }
  ],
  "location": "WebServiceStepDefinition.a_is_than(String,String,String,int,String)"
});
formatter.result({
  "duration": 1134933,
  "status": "passed"
});
formatter.after({
  "duration": 285105677,
  "status": "passed"
});
});