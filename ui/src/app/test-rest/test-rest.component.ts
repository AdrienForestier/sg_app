import { Component } from '@angular/core';
import {RestFacadeService} from "../service/rest-facade.service";
import {SalesDTO, TableInfoDTO} from "../ext";

@Component({
  selector: 'app-test-rest',
  templateUrl: './test-rest.component.html'
})
export class TestRestComponent {

  salesTableInfo: TableInfoDTO = {cols:[]};
  salesTableInfoJson= '';

  salesFirstRows: SalesDTO[] = [];
  salesFirstRowsJson = '';

  constructor(private restFacadeService: RestFacadeService) {}

  onClickGetTableInfoSales() {
    this.restFacadeService.getTableInfoSales().subscribe({ next: res => {
        this.salesTableInfo = res;
        this.salesTableInfoJson = JSON.stringify(res, null, 2);
        console.log('done get Sales table info')
      }, error: err => {
        console.log('Failed', err)
      }});
  }

  onClickQuery() {
    this.restFacadeService.queryFirstSales().subscribe({ next: res => {
      this.salesFirstRows = res;
      this.salesFirstRowsJson = JSON.stringify(res, null, 2);
      console.log('done query Sales first rows')
    }, error: err => {
      console.log('Failed', err)
    }});
  }

}
