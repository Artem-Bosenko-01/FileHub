/**
 *
 * @returns {(function(...[*]=): void)|*}
 */
export function getSpy() {
  const _callsSpy = [];
  return function(...args) {
    _callsSpy.push(args);
    return {
      getCalls: function() {
        return _callsSpy;
      },
    };
  };
}
