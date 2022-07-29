import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../shared/api.config';
import { environment } from 'src/environments/environment';
import { AnyPageFilter } from '../model/rest/filter';
import { DataSourceRESTResponse, RESTResponse } from '../model/rest/response';
import { Contact } from '../model/contact';
import { CreateContactRequest, EditContactRequest } from '../model/rest/request';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private http: HttpClient) { }

  public getContacts(pageFilter: AnyPageFilter): Observable<DataSourceRESTResponse<Contact[]>> {
    const url = API_CONFIG.getContacts;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
    });
    return this.http.post<DataSourceRESTResponse<Contact[]>>(url, pageFilter, { headers });
  }

  public getContact(id: number): Observable<RESTResponse<Contact>> {
    const url = API_CONFIG.getContact;
    const headers = new HttpHeaders({
      'Content-type': 'charset=utf-8',
      Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
    });
    const params = new HttpParams().set('id', id.toString());
    return this.http.get<RESTResponse<Contact>>(url, { params, headers });
  }

  public createContact(contact: Contact): Observable<RESTResponse<number>> {
    const url = API_CONFIG.createContact;
    const body: CreateContactRequest = new CreateContactRequest(contact);
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
    });
    return this.http.post<RESTResponse<number>>(url, body, { headers });
  }

  public editContact(contact: Contact): Observable<RESTResponse<number>> {
    const url = API_CONFIG.editContact;
    const body: EditContactRequest = new EditContactRequest(contact);
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
    });
    return this.http.post<RESTResponse<number>>(url, body, { headers });
  }

  public deleteContact(id: number): Observable<RESTResponse<number>> {
    const url = API_CONFIG.deleteContact;
    const headers = new HttpHeaders({
      'Content-type': 'charset=utf-8',
      Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
    });
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete<RESTResponse<number>>(url, { params, headers });
  }
}
