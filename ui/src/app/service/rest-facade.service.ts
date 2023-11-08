import { Injectable } from '@angular/core';
import {SalesDTO, SalesRestService, TableInfoDTO} from "../ext";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RestFacadeService {

  constructor(private salesRestService: SalesRestService) { }

  getTableInfoSales(): Observable<TableInfoDTO> {
    return this.salesRestService.tableInfo2();
  }
  queryFirstSales(): Observable<SalesDTO[]> {
    return this.salesRestService.first2();
  }

}
