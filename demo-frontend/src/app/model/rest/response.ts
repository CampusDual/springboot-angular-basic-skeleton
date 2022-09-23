// import { User } from '../user';
// import { Profile } from '../profile';
// import { Section } from '../section';



export class DataSourceRESTResponse<T> {
  totalElements: number;
  responseCode: string;
  responseMessage: string;
  data: T;
}

// export class ProfileResponse {
//   data: Profile[];
// }

// export class SectionResponse {
//   data: Section[];
// }
