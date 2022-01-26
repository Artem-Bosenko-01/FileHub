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
    {
      id: 'ac787s',
      name: 'file-pdf',
      type: 'file',
      mimeType: 'application/pdf',
      size: 984948,
      parentFolderId: 'root-folder',
    },
    {
      id: 'rer554',
      name: 'file-video',
      type: 'file',
      mimeType: 'video/x-msvideo',
      size: 7447474,
      parentFolderId: 'root-folder',
    },
    {id: 'fold777', name: 'folder-1011', type: 'folder', itemsAmount: 7, parentFolderId: 'root-folder'},
    {id: '595cz', name: 'folder-595', type: 'folder', itemsAmount: 0, parentFolderId: 'root-folder'},
    {id: 'fold777', name: 'Inner_folder', type: 'folder', itemsAmount: 77, parentFolderId: 'root-folder'},
    {id: 'sdv66sa', name: 'ARMYANE', type: 'folder', itemsAmount: 949, parentFolderId: 'fold777'},
    {id: '74kgf', name: 'KAZAHI', type: 'folder', itemsAmount: 1488, parentFolderId: 'fold777'},
  ],

  itemsContent: [
    {id: 'ac787s', content: '56489465scasq65'},
    {id: 'rer554', content: 'scsasc9s874sa6c'},
  ],

  getFolderById: function(id) {
    return this.items.find((item) => item.id === id);
  },
  getFolderByParentId: function(parentId) {
    if (!this.items.find((item) => item.id === parentId)) {
      throw new Error();
    }
    return this.items.filter((item) => item.parentFolderId === parentId);
  },
  getRootFolder: function() {
    return this.items.find((item) => !item.parentFolderId && item.type === 'folder');
  },
  deleteFolder: function(id) {
    this.items = this.items.filter((item) => !(item.id === id && item.type === 'folder'));
  },
  deleteFile: function(id) {
    this.items = this.items.filter((item) => !(item.id === id && item.type === 'file'));
  },
  addFile: function(id, name, mimeType, size, parentFolderId) {
    if (this.items.find((item) => item.name === name && item.parentFolderId === parentFolderId)) {
      throw new Error('This item\'s name also exist');
    } else {
      this.items.push({id, name, type: 'file', mimeType, size, parentFolderId});
    }
  },
  getFileById: function(id) {
    const file = this.items.find((item) => item.id === id && item.type === 'file');
    if (!file) {
      throw new Error('This item\'s name also exist');
    } else {
      return file;
    }
  },
  getFileContent: function(id) {
    const file = this.itemsContent.find((item) => item.id === id);
    if (!file) {
      throw new Error('This item\'s name also exist');
    } else {
      return file.content;
    }
  },
  addDirectory: function(name, type, itemsAmount, parentFolderId) {
    if (this.items.find((item) => item.name === name && item.parentFolderId === parentFolderId)) {
      throw new Error('This item\'s name also exist');
    } else {
      const id = `${name}_${parentFolderId}`;
      this.items.push({id, name, type, itemsAmount, parentFolderId});
    }
  },
  renameFolder: function(id, newName) {
    const item = this.items.find((item) => item.id === id && item.type === 'folder');
    if (this.items.find((item) => item.name === newName && item.type === 'folder')) {
      throw new Error('This folder name are exist');
    } else {
      item.name = newName;
    }
  },
  renameFile: function(id, newName) {
    const item = this.items.find((item) => item.id === id && item.type === 'file');
    if (this.items.find((item) => item.name === newName && item.type === 'file')) {
      throw new Error('This file name are exist');
    } else {
      item.name = newName;
    }
  },
};

const mockPostRequest = (url, handler) => {
  fetchMock.post(url, (...args) => {
    const response = handler(...args);
    console.log(...args, response);
    return response;
  }, {delay: 4000});
};

const mockGetRequest = (url, handler) => {
  fetchMock.get(url, (...args) => {
    const response = handler(...args);
    console.log(...args, response);
    return response;
  }, {delay: 400});
};

const mockDeleteRequest = (url, handler) => {
  fetchMock.delete(url, (...args) => {
    const response = handler(...args);
    console.log(...args, response);
    return response;
  }, {delay: 400});
};

const mockPutRequest = (url, handler) => {
  fetchMock.put(url, (...args) => {
    const response = handler(...args);
    console.log(...args, response);
    return response;
  }, {delay: 400});
};

function checkToken(token) {
  if (!token || (token === 'null')) {
    throw new Error();
  }
}

mockPostRequest('/login', (url, opts) => {
  const body = opts.body;
  const email = JSON.parse(body).email;
  const password = JSON.parse(body).password;

  if (registeredUsers.has(email)) {
    if (registeredUsers.get(email) === password) {
      return {token: 'vsds'};
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
  const token = opts.headers.get('Authorization').split(' ')[1];
  try {
    checkToken(token);
  } catch (error) {
    return 401;
  }
  const rootFolder = itemDatabase.getRootFolder();
  return {folder: rootFolder};
});

mockGetRequest('express:/folder/:id', (url, opts) => {
  const token = opts.headers.get('Authorization').split(' ')[1];

  try {
    checkToken(token);
  } catch (error) {
    return 401;
  }
  const id = url.split('/')[2];
  const folder = itemDatabase.getFolderById(id);
  return {folder};
});

mockGetRequest('express:/folder/:id/content', (url, opts) => {
  const token = opts.headers.get('Authorization').split(' ')[1];
  try {
    checkToken(token);
  } catch (error) {
    return 401;
  }
  const parentId = url.split('/')[2];
  try {
    const content = itemDatabase.getFolderByParentId(parentId);
    return {items: content};
  } catch (error) {
    return 404;
  }
});

mockGetRequest('/user', (url, opts) => {
  const token = opts.headers.get('Authorization').split(' ')[1];
  try {
    checkToken(token);
  } catch (error) {
    return 401;
  }
  return {
    name: 'Artem Bosenko',
    id: '4521a4sca',
  };
});

mockDeleteRequest('express:/folder/:id', (url, opts) => {
  const token = opts.headers.get('Authorization').split(' ')[1];
  try {
    checkToken(token);
  } catch (error) {
    return 401;
  }
  const id = url.split('/')[2];
  itemDatabase.deleteFolder(id);
  return {
    status: 200,
  };
});

mockDeleteRequest('express:/file/:id', (url, opts) => {
  const token = opts.headers.get('Authorization').split(' ')[1];
  try {
    checkToken(token);
  } catch (error) {
    return 401;
  }
  const id = url.split('/')[2];
  itemDatabase.deleteFile(id);
  return {
    status: 200,
  };
});

mockPostRequest('express:/folder/:id/file', (url, opts) => {
  const token = opts.headers.get('Authorization').split(' ')[1];
  try {
    checkToken(token);
  } catch (error) {
    return 401;
  }
  const body = opts.body.get('file');
  const id = url.split('/')[2];
  try {
    itemDatabase.addFile(body.name, body.name, body.type, body.size, id);
    return {
      id: body.name,
      name: body.name,
      type: body.type,
      parentId: id,
    };
  } catch (error) {
    return {
      status: 400,
    };
  }
});

mockGetRequest(`express:/file/:id`, (url, opts) => {
  const token = opts.headers.get('Authorization').split(' ')[1];
  try {
    checkToken(token);
  } catch (error) {
    return 401;
  }
  const id = url.split('/')[2];
  try {
    const fileContent = itemDatabase.getFileContent(id);
    const file = itemDatabase.getFileById(id);
    return {
      name: file.name,
      mimeType: file.mimeType,
      content: fileContent,
    };
  } catch (error) {
    return {
      status: 400,
    };
  }
});

mockPostRequest(`express:/folder/:id/folder`, (url, opts) => {
  const token = opts.headers.get('Authorization').split(' ')[1];
  try {
    checkToken(token);
  } catch (error) {
    return 401;
  }
  const id = url.split('/')[2];
  try {
    const body = JSON.parse(opts.body);
    itemDatabase.addDirectory(body.name, body.type, body.itemsAmount, id);
    return {
      file: body.name,
    };
  } catch (error) {
    return {
      status: 400,
    };
  }
});

mockPostRequest('/logOut', (url, opts) => {
  return 200;
});

mockPutRequest(`express:/folder/:id`, (url, opts) => {
  const token = opts.headers.get('Authorization').split(' ')[1];
  try {
    checkToken(token);
  } catch (error) {
    return 401;
  }

  try {
    const body = JSON.parse(opts.body);
    itemDatabase.renameFolder(body.id, body.name);
    return {
      id: body.id,
      name: body.name,
    };
  } catch (error) {
    return {
      status: 400,
    };
  }
});


mockPutRequest(`express:/file/:id`, (url, opts) => {
  const token = opts.headers.get('Authorization').split(' ')[1];
  try {
    checkToken(token);
  } catch (error) {
    return 401;
  }

  try {
    const body = JSON.parse(opts.body);
    itemDatabase.renameFile(body.id, body.name);
    return {
      id: body.id,
      name: body.name,
    };
  } catch (error) {
    return {
      status: 400,
    };
  }
});
