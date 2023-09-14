export interface ConfirmResponse {

  loanApplicationNumber: string;
  requestedAmount: number;
  status: string;
  createAt: number[]; // Esto puede cambiar según cómo quieras representar la fecha
  name: string;
  lastname: string;
  customersUuid: string;
  identification: string;
  phone: string;


}
