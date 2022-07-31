import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Contact } from 'src/app/model/contact';
import { ContactService } from 'src/app/services/contact.service';
import { RESTResponse } from 'src/app/model/rest/response';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { LoggerService } from 'src/app/services/logger.service';

@Component({
  templateUrl: './edit-contact.component.html',
  styleUrls: ['./edit-contact.component.scss'],
})
export class EditContactComponent implements OnInit {
  idContact: number;

  contactForm: FormGroup;
  contact: Contact;

  constructor(
    private fb: FormBuilder,
    private contactService: ContactService,
    private router: Router,
    private route: ActivatedRoute,
    private logger: LoggerService
  ) {
    this.contact = new Contact();
  }

  ngOnInit() {
    this.createFormGroup();
    this.idContact = this.route.snapshot.params['id'];
    if (this.idContact) {
      this.contactService.getContact(this.idContact).subscribe(
        response => {
          this.contact = response.data;
          this.contactForm.patchValue(this.contact, { emitEvent: false, onlySelf: false });
          this.logger.info(this.contact);
        }
      );
    }
  }



  onFormChanges() {
    this.contactForm.valueChanges.subscribe((val) => {});
  }

  createFormGroup() {
    this.contactForm = this.fb.group({
      id: [this.contact.id],
      name: [this.contact.name, Validators.required],
      surname1: [this.contact.surname1, Validators.required],
      surname2: [this.contact.surname2],
      phone: [this.contact.phone, Validators.required],
      email: [this.contact.email],
    });
  }

  save() {
    const newContact: Contact = Object.assign({}, this.contactForm.value);
    if (newContact.id) {
      this.contactService.editContact(newContact).subscribe((response) => {
        this.redirectList(response);
      });
    } else {
      this.contactService.createContact(newContact).subscribe((response) => {
        this.redirectList(response);
      });
    }
  }

  redirectList(response: RESTResponse<number>) {
    if (response.responseCode === 'OK') {
      // const newContact: Contact = Object.assign({}, this.contactForm.value);
      this.router.navigate(['/contacts']);
    }
  }

  compareObjects(o1: any, o2: any): boolean {
    if (o1 && o2) {
      return o1.id === o2.id;
    } else {
      return false;
    }
  }

  cancel() {
    // this.saveDetails.emit();
    this.router.navigate(['/contacts']);
  }
}
