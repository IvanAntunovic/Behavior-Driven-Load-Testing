$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("BDLT_Web_Service.feature");
formatter.feature({
  "line": 1,
  "name": "Response of a web-page",
  "description": "",
  "id": "response-of-a-web-page",
  "keyword": "Feature"
});
formatter.before({
  "duration": 770560,
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
  "name": "\"run\" the experiment for 4 \"hours\"",
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "\"ensure\" a \"positive answer\" should return",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "\"ensure\" a \"response time\" is \"less\" than 5000 \"miliseconds\"",
  "keyword": "And "
});
formatter.match({
  "location": "WebServiceStepDefinition.the_next_beginning_of_Summer_Term()"
});
formatter.result({
  "duration": 475601514,
  "status": "passed"
});
formatter.match({});
formatter.result({
  "status": "undefined"
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
  "status": "skipped"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.after({
  "duration": 69974,
  "status": "passed"
});
});