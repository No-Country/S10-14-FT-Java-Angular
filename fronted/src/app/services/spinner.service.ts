import { Injectable } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';

@Injectable({
  providedIn: 'root'
})
export class SpinnerService {

  constructor(private spinnerService: NgxSpinnerService) { }

  public startSpinner(){
    setTimeout(() => {
     this.spinnerService.show();
    }, 2000);

  }
  public stopSpinner(){
    this.spinnerService.hide();
  }
}
