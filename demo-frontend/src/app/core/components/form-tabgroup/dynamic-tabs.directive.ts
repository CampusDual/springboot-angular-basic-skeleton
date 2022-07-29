import { Directive, ViewContainerRef, Input } from '@angular/core';

@Directive({
  selector: '[dynamic-tabs]'
})
export class DynamicTabsDirective {
  @Input() index: number;
  constructor(public viewContainer: ViewContainerRef) {}
}
