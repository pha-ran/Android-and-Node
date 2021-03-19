var express = require('express');
var app = express();

app.get('/', function(req, res) {
  res.send('Hello World!');
});

var port = 2021;
app.listen(port, function(){
  console.log('server on! http://localhost:'+port);
});
