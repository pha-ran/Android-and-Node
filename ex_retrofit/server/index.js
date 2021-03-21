var express = require('express');
var app = express();
var bodyParser = require('body-parser');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));

app.get('/', function(req, res) {
  console.log("get '/'");
});

app.get('/data', function(req, res) {
  console.log("get '/data'");
  res.json({id:1, name:'json'});
});

app.post('/data', function(req, res) {
  console.log("post '/data'");
  console.log(req.body);
});

var port = 2021;
app.listen(port, function(){
  console.log('server on! http://localhost:'+port);
});
