import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError, map, Observable, throwError } from 'rxjs';
import { API_CONFIG } from '../shared/api.config';
import { environment } from 'src/environments/environment';
import { AnyPageFilter } from '../model/rest/filter';
import { DataSourceRESTResponse } from '../model/rest/response';
import { Contact } from '../model/contact';
import { CreateContactRequest, EditContactRequest } from '../model/rest/request';
import { Buffer } from 'buffer';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private http: HttpClient) { }

  public getContacts(pageFilter: AnyPageFilter): Observable<DataSourceRESTResponse<Contact[]>> {
    const url = API_CONFIG.getContacts;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      // Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
      Authorization: 'Basic ' + Buffer.from(`${environment.clientName}:${environment.clientSecret}`, 'utf8').toString('base64'),
    });
    return this.http.post<DataSourceRESTResponse<Contact[]>>(url, pageFilter, { headers });
  }

  public getContact(id: number): Observable<Contact> {
    const url = API_CONFIG.getContact;
    const headers = new HttpHeaders({
      'Content-type': 'charset=utf-8',
      Authorization: 'Basic ' + Buffer.from(`${environment.clientName}:${environment.clientSecret}`, 'utf8').toString('base64'),
    });
    const params = new HttpParams().set('id', id.toString());
    return this.http.get<Contact>(url, { params, headers });
  }

  public createContact(contact: Contact): Observable<any> {
    const url = API_CONFIG.createContact;
    const body: CreateContactRequest = new CreateContactRequest(contact);
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      Authorization: 'Basic ' + Buffer.from(`${environment.clientName}:${environment.clientSecret}`, 'utf8').toString('base64'),
    });
    return this.http.post<Contact>(url, body, { headers }).pipe(
      catchError(e =>{
        return throwError(()=>e);
      })
    );
  }

  public editContact(contact: Contact): Observable<any> {
    const url = API_CONFIG.editContact;
    const body: EditContactRequest = new EditContactRequest(contact);
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      Authorization: 'Basic ' + Buffer.from(`${environment.clientName}:${environment.clientSecret}`, 'utf8').toString('base64'),
    });
    return this.http.post<any>(url, body, { headers }).pipe(
      catchError((e:HttpErrorResponse) =>{
        return throwError(()=>e);
      })
    );
  }

  public deleteContact(id: number): Observable<any> {
    const url = API_CONFIG.deleteContact;
    const headers = new HttpHeaders({
      'Content-type': 'charset=utf-8',
      Authorization: 'Basic ' + Buffer.from(`${environment.clientName}:${environment.clientSecret}`, 'utf8').toString('base64'),
    });
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete<any>(url, { params, headers });
  }
}
