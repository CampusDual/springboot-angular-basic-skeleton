import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-form-tab',
  templateUrl: './form-tab.component.html',
  styleUrls: ['./form-tab.component.scss']
})
export class FormTabComponent {
  @Input() title: string;
  @Input() active = false;
  @Input() isCloseable = false;
  @Input() template: any;
  @Input() dataContext: any;
  @Input() modified = false;
  @Input() cancel = true;
  @Input() index: number;
}
