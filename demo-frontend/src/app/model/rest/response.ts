// import { User } from '../user';
// import { Profile } from '../profile';
// import { Section } from '../section';

export class RESTResponse<T> {
  responseCode: string;
  responseMessage: string;
  data: T;
}

export class DataSourceRESTResponse<T> extends RESTResponse<T> {
  totalElements: number;
}

// export class ProfileResponse {
//   data: Profile[];
// }

// export class SectionResponse {
//   data: Section[];
// }
