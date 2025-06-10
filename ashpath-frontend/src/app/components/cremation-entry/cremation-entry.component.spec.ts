import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CremationEntryComponent } from './cremation-entry.component';

describe('CremationEntryComponent', () => {
  let component: CremationEntryComponent;
  let fixture: ComponentFixture<CremationEntryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CremationEntryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CremationEntryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
