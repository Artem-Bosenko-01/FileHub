import fetchMock from '../node_modules/fetch-mock/esm/client.js';

const registeredUsers = new Map()
    .set('qwe@qwe.qwe', '123456')
    .set('artem.vvv@hand', '654321')
    .set('vasya@kakk', '123654')
    .set('email.jj@jash.com', '654123');

fetchMock.post('/login', (url, opts) => {
  const body = opts.body;
  const email = JSON.parse(body).email;
  const password = JSON.parse(body).password;

  if (registeredUsers.has(email)) {
    if (registeredUsers.get(email) === password) {
      return {token: 'AUTH_TOKEN'};
    } else {
      return {
        status: 400,
        body: {message: 'Incorrect password'},
      };
    }
  } else {
    return {
      status: 403,
      body: {message: 'No registered user'},
    };
  }
});

fetchMock.post('/register', (url, opts) => {
  const body = JSON.parse(opts.body);
  const email = body.email;

  if (registeredUsers.has(email)) {
    return {
      status: 422,
      body: {errors: [{field: 'email', message: `User with this email already existed`}]},
    };
  } else {
    return {
      status: 200,
    };
  }
});

fetchMock.get('/folder/:root-folder', () => {
  return {
    folder: {
      id: 'root-folder',
      name: 'ROOT',
      type: 'folder',
      itemsAmount: 45,
    },
  };
});

fetchMock.get('/folder/:fold777', () => {
  return {
    folder: {
      id: 'fold777',
      name: 'Inner_folder',
      type: 'folder',
      itemsAmount: 77,
      parentFolderId: 'root-folder',
    },
  };
});

fetchMock.get('/root-folder', () => {
  return {
    folder: {
      id: 'root-folder',
      name: 'root-folder',
      type: 'folder',
      itemsAmount: 45,
    },
  };
});

fetchMock.get('/folder/:root-folder/content', () => {
  return {
    items: [{id: 'scas8988', name: 'folder-8988', type: 'folder', itemsAmount: 45, parentFolderId: 'root-folder'},
      {id: 'ac787s', name: 'file-pdf', type: 'file', mimeType: 'pdf', size: 984948, parentFolderId: 'root-folder'},
      {id: 'rer554', name: 'file-video', type: 'file', mimeType: 'video', size: 7447474, parentFolderId: 'root-folder'},
      {id: 'fold777', name: 'folder-1011', type: 'folder', itemsAmount: 7, parentFolderId: 'root-folder'},
      {id: '595cz', name: 'folder-595', type: 'folder', itemsAmount: 0, parentFolderId: 'root-folder'},
    ],
  };
});

fetchMock.get('/folder/:fold777/content', () => {
  return {
    items: [],
  };
});

/* fetchMock.post('/register', () => {
  return {
    status: 422,
    body: [
      {field: 'email', message: 'this is message'},
      {field: 'password', message: 'this is password message'},
    ],
  };
});*/

/*
fetchMock.post('/register', () => {
  return {
    status: 500,
    body: {
      message: 'This is server error',
    },
  };
});
*/
