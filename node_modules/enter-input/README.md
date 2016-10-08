
# enter-input

A html input element that calls back on <enter>.

## Example

```js
var EnterInput = require('enter-input');

var input = EnterInput(function(ev) {
  console.log(this.value);
  this.value = '';
});

document.body.appendChild(input);
```

## API

### var el = EnterInput([el, ]fn)

Create/Take and return a html input element. Whenever someone hits `<enter>` inside
the input, `fn` is called with the event.

`this` is the actual element, so for example `this.value` has the input's
current value.

## Installation

With [npm](https://npmjs.org) do:

```bash
npm install enter-input
```

Then bundle for the browser with
[browserify](https://github.com/substack/node-browserify).

## License

(MIT)

Copyright (c) 2013 Julian Gruber &lt;julian@juliangruber.com&gt;

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
of the Software, and to permit persons to whom the Software is furnished to do
so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
