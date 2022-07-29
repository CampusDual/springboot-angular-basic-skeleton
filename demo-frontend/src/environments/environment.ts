// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiBaseUrl: 'http://localhost:9999' ,
  authBaseUrl: 'http://localhost:9999',
  adminBaseUrl: 'http://localhost:9999',
  usersBaseUrl: 'http://localhost:9999/users',
  loginBaseUrl: 'http://localhost:9999/login',
  profilesBaseUrl: 'http://localhost:9999/profiles',
  clientName: 'demo',
  clientSecret: '8Fjkk59bXKws8bmMNFZB',
  idle: 1,
  idleTimeout: 900,
  idlePingInterval: 15
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
