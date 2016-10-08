module.exports = Input;

/**
 * Enter input.
 *
 * @param {Element=} el
 * @param {Function} fn
 */

function Input(el, fn) {
  if (typeof el == 'function') {
    fn = el;
    el = document.createElement('input');
  }

  el.addEventListener('keydown', function(ev) {
    if (ev.keyCode == 13) return fn.call(el, ev);
  });

  return el;
}

