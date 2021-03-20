var express = require('express');
var app = express();

app.get('/', function(req, res) {
  console.log("get '/'");
});

app.get('/data', function(req, res) {
  console.log("get '/data'");
  res.json({id:1, name:'json'});
});

var port = 2021;
app.listen(port, function(){
  console.log('server on! http://localhost:'+port);
});
