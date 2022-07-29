import { DataSource } from '@angular/cdk/table';
import { BehaviorSubject } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { ContactService } from 'src/app/services/contact.service';
import { Contact } from '../contact';
import { AnyPageFilter } from '../rest/filter';

export class ContactDataSource extends DataSource<Contact> {
  contactsSubject = new BehaviorSubject<Contact[]>([]);
  loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();
  public totalElements: number;

  constructor(private contactService: ContactService) {
    super();
  }

  getContacts(pageFilter: AnyPageFilter) {
    this.contactsSubject.next([]);
    this.loadingSubject.next(true);
    this.contactService.getContacts(pageFilter).pipe(
      finalize(() => this.loadingSubject.next(false))
    ).subscribe(
      response => {
        this.totalElements = response.totalElements;
        this.contactsSubject.next(response.data);
      }
    );
  }

  connect(): BehaviorSubject<Contact[]> {
    return this.contactsSubject;
  }

  disconnect(): void {
    this.contactsSubject.complete();
    this.loadingSubject.complete();
  }
}
