import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserApprovalPageComponent } from './user-approval-page.component';

describe('UserApprovalPageComponent', () => {
  let component: UserApprovalPageComponent;
  let fixture: ComponentFixture<UserApprovalPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserApprovalPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserApprovalPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
