import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormTabgroupComponent } from './form-tabgroup.component';

describe('FormTabgroupComponent', () => {
  let component: FormTabgroupComponent;
  let fixture: ComponentFixture<FormTabgroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormTabgroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormTabgroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
