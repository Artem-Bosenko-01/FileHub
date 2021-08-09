import fetchMock from '../node_modules/fetch-mock/esm/client.js';

const registeredUsers = new Map()
    .set('qwe@qwe.qwe', '123456')
    .set('artem.vvv@hand', '654321')
    .set('vasya@kakk', '123654')
    .set('email.jj@jash.com', '654123');

const itemDatabase = {
  items: [
    {id: 'root-folder', name: 'root-folder', type: 'folder', itemsAmount: 45},
    {id: 'scas8988', name: 'folder-8988', type: 'folder', itemsAmount: 45, parentFolderId: 'root-folder'},
    {id: 'ac787s', name: 'file-pdf', type: 'file', mimeType: 'pdf', size: 984948, parentFolderId: 'root-folder'},
    {id: 'rer554', name: 'file-video', type: 'file', mimeType: 'video', size: 7447474, parentFolderId: 'root-folder'},
    {id: 'fold777', name: 'folder-1011', type: 'folder', itemsAmount: 7, parentFolderId: 'root-folder'},
    {id: '595cz', name: 'folder-595', type: 'folder', itemsAmount: 0, parentFolderId: 'root-folder'},
    {id: 'fold777', name: 'Inner_folder', type: 'folder', itemsAmount: 77, parentFolderId: 'root-folder'},
    {id: 'sdv66sa', name: 'ARMYANE', type: 'folder', itemsAmount: 949, parentFolderId: 'fold777'},
    {id: '74kgf', name: 'KAZAHI', type: 'folder', itemsAmount: 1488, parentFolderId: 'fold777'},
  ],
  getFolderById: function(id) {
    return this.items.find((item) => item.id === id);
  },
  getFolderByParentId: function(parentId) {
    return this.items.filter((item) => item.parentFolderId === parentId);
  },
  getRootFolder: function() {
    return this.items.find((item) => !item.parentFolderId && item.type === 'folder');
  },
};

const mockPostRequest = (url, handler) => {
  fetchMock.post(url, (...args) => {
    const response = handler(...args);
    console.log(...args, response);
    return response;
  });
};

const mockGetRequest = (url, handler) => {
  fetchMock.get(url, (...args) => {
    const response = handler(...args);
    console.log(...args, response);
    return response;
  }, {delay: 2000});
};

mockPostRequest('/login', (url, opts) => {
  const body = opts.body;
  const email = JSON.parse(body).email;
  const password = JSON.parse(body).password;

  if (registeredUsers.has(email)) {
    if (registeredUsers.get(email) === password) {
      return {token: 'AUTH_TOKEN'};
    } else {
      return {
        status: 401,
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

mockPostRequest('/register', (url, opts) => {
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

mockGetRequest('/root-folder', (url, opts) => {
  const rootFolder = itemDatabase.getRootFolder();
  return {folder: rootFolder};
});

mockGetRequest('express:/folder/:id', (url, opts) => {
  const id = url.split('/')[2];
  const folder = itemDatabase.getFolderById(id);
  return {folder};
});

mockGetRequest('express:/folder/:id/content', (url, opts) => {
  const parentId = url.split('/')[2];
  const content = itemDatabase.getFolderByParentId(parentId);
  return {items: content};
});

mockGetRequest('/user', (url, opts) => {
  return {
    user: {
      name: 'Artem Bosenko',
      id: '4521a4sca',
    },
  };
});
