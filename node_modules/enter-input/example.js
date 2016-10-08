
var Input = require('./');

var input = Input(function(ev) {
  console.log(this.value);
  this.value = '';
});

document.body.appendChild(input);

