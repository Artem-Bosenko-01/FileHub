import fetchMock from '../node_modules/fetch-mock/esm/client.js';

fetchMock.post('/login', {token: 'AUTH_TOKEN'});

fetchMock.post('/register', (url, opts) => {
  const body = opts.body;
  const email = JSON.parse(body).email;
  const password = JSON.parse(body).password;
  return {
    email: email,
    password: password,
  };
});
