import { environment } from '../../environments/environment';

export const API_CONFIG = {
  authUrl: environment.authBaseUrl,
  login: environment.authBaseUrl + '/oauth/token',
  logout: environment.authBaseUrl + '/logout',
  canLogin: environment.loginBaseUrl + '/canLogin',
  getAllProfiles: environment.adminBaseUrl + '/getAllProfiles',
  getAllSections: environment.adminBaseUrl + '/getAllSections',

  // Users API
  getUsers: environment.usersBaseUrl + '/getUsers',
  getUser: environment.usersBaseUrl + '/getUser',
  createUser: environment.usersBaseUrl + '/createUser',
  editUser: environment.usersBaseUrl + '/editUser',
  deleteUser: environment.usersBaseUrl + '/deleteUser',


  // Profiles API
  getProfiles: environment.profilesBaseUrl + '/getProfiles',
  getProfile: environment.profilesBaseUrl + '/getProfile',
  createProfile: environment.profilesBaseUrl + '/createProfile',
  editProfile: environment.profilesBaseUrl + '/editProfile',
  deleteProfile: environment.profilesBaseUrl + '/deleteProfile',
  cloneProfile: environment.profilesBaseUrl + '/cloneProfile',
};
