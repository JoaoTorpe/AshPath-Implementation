import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateDeceasedComponent } from './create-deceased.component';

describe('CreateDeceasedComponent', () => {
  let component: CreateDeceasedComponent;
  let fixture: ComponentFixture<CreateDeceasedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateDeceasedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateDeceasedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
