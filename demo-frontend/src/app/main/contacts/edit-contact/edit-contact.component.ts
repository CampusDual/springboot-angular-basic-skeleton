import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Contact } from 'src/app/model/contact';
import { ContactService } from 'src/app/services/contact.service';
import { RESTResponse } from 'src/app/model/rest/response';

@Component({
  templateUrl: './edit-contact.component.html',
  styleUrls: ['./Edit-contact.component.scss']
})
export class EditContactComponent implements OnInit {

  contactForm: FormGroup;
  contact: Contact;

  constructor(private fb: FormBuilder, private contactService: ContactService) {
    this.contact = new Contact();
  }

  ngOnInit() {
    this.createFormGroup();
  }

  onFormChanges() {
    this.contactForm.valueChanges.subscribe(val => {
    });
  }

  createFormGroup() {
    this.contactForm = this.fb.group({
      id: [this.contact.id],
      name: [this.contact.name, Validators.required],
      surname1: [this.contact.surname1, Validators.required],
      surname2: [this.contact.surname2],
      phone: [this.contact.phone],
      email: [this.contact.email, Validators.required],
    });
  }

  save() {
    const newContact: Contact = Object.assign({}, this.contactForm.value);
    if (newContact.id) {
      this.contactService.editContact(newContact).subscribe(
        response => {
          this.redirectList(response);
        }
      );
    } else {
      this.contactService.createContact(newContact).subscribe(
        response => {
          this.redirectList(response);
        }
      );
    }
  }

  redirectList(response: RESTResponse<number>) {
    if (response.responseCode === 'OK') {
      const newContact: Contact = Object.assign({}, this.contactForm.value);
      // const responseTab: IResponseTab = {
        // data: newContact,
        // new: this.idContact !== null,
        // id: response.data
      // };
      // this.saveDetails.emit(responseTab);
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
  }

}
