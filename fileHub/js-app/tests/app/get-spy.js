/**
 * Spy to control calls parameters in the function.
 * @returns {{calls: [], getMethod: (function(any))}}
 */
export function getSpy() {
  const _callsSpy = [];

  return {
    calls: _callsSpy,
    getMethod: (...args) => _callsSpy.push(args),
  };
}
