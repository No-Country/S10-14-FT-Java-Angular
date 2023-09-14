import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { GeneralDataRequest } from '../models/general-data-request';
import { Observable, catchError, throwError } from 'rxjs';
import { GeneralDataResponse } from '../models/general-data-response';
import { JobInformationRequest } from '../models/job-information-request';
import { GuarantorRequest } from '../models/guarantor-request';
import { ConfirmResponse } from '../models/confirm-response';


@Injectable({
  providedIn: 'root'
})
export class LoansApplicationService {
  apiUrl = environment.apiUrlLoanApp;

  constructor(private http: HttpClient) { }

  public saveGeneralData(data: GeneralDataRequest):Observable<GeneralDataResponse>{
    return this.http.post<GeneralDataResponse>(this.apiUrl + 'generaldata', data).pipe(
      catchError(this.handleError)
    );
  }

  public saveJobInformation(data: JobInformationRequest):Observable<void>{
    return this.http.post<void>(this.apiUrl + 'jobinformation', data);
  }

  public saveGuarantor(data: GuarantorRequest): Observable<any>{
    return this.http.post<any>(this.apiUrl + 'guarantor', data).pipe(
      catchError(this.handleError)
    )
  }

  public findLoanApplication(id: string):Observable<ConfirmResponse>{
    return this.http.get<ConfirmResponse>(this.apiUrl + 'loanapplication/' + 'confirm/' + `${id}`);
  }

  private handleError(err:HttpErrorResponse){
    if(err.status === 0 )
      console.error('Se ha producio un error ', err.error);
    if(err.status === 400)
      return throwError(() => new Error('No se puede crear una nueva solicitud porque ya tiene una en estado AUDITORÍA o INCOMPLETA'));
    else{
      return throwError(()=> new Error('Algo falló. Por favor intente nuevamente.'));
    }

  }
}
