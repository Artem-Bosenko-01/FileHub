/**
 * Window mock for testing api service methods.
 * @param {fetchMock.FetchMockSandbox} fetch
 * @returns {{fetch, localStorage: {getItem: (function(): string), setItem: localStorage.setItem}}}
 */
export function getWindowMock(fetch) {
  return {
    fetch, localStorage: {
      setItem: function(token) {
        this.token = token;
      },
      getItem: function() {
        return this.token;
      },
      removeItem: function() {
        this.token = '';
      },
    },
  };
}
