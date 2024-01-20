import { Component } from '@angular/core';
import {RestFacadeService} from "../service/rest-facade.service";
import {SalesDTO, TableInfoDTO} from "../ext";

@Component({
  selector: 'app-test-rest',
  templateUrl: './test-rest.component.html'
})
export class TestRestComponent {

  salesTableInfo: TableInfoDTO = {schema: { fields:[] }};
  salesTableInfoJson= '';

  salesFirstRows: SalesDTO[] = [];
  salesFirstRowsJson = '';
  fieldNames: string[]= [];
  fieldDataTypes: string[] = [];
  namesDataTypes: { [fieldName: string]: string } = {};
  dropdownValues: string[] = ['Category', 'Date', 'Event', 'Listing', 'Sales', 'User', 'Venue'];
  factsTables: string[] = ['Listing', 'Sales'];
  listingTables: string[] = ['Category', 'Date', 'Event', 'User', 'Venue'];
  selectedValue: string = '';
  selectedValueDropDown2: string = '';
  selectedValueDropDown3: string = '';
  selectedTextArea: string = '';
  filtersToReturn: string[] = [];
  filter: string[] = [];
  filterList : string[][] = [];

  constructor(private restFacadeService: RestFacadeService) {

  }

  /*getFieldType(res: any){
    this.salesTableInfo = res;
    this.salesTableInfoJson = JSON.stringify(res, null, 2);
    if (res.schema?.fields) {
      const nameDataTypes: { [key : string] : string } = {};
      this.fieldNames = res.schema.fields!.map(x => x.name!);
      // @ts-ignore
      this.fieldDataTypes = res.schema.fields!.map(x => x.dataType.type);
      this.fieldNames.forEach((fieldName, index) => {
        nameDataTypes[fieldName] = this.fieldDataTypes[index];
      });
      this.namesDataTypes = nameDataTypes;
    }
  }*/

  onClickGetTableInfoSales() {
    this.restFacadeService.getTableInfoSales().subscribe({ next: res => {
        this.filter = [];
        this.salesTableInfo = res;
        this.salesTableInfoJson = JSON.stringify(res, null, 2);
        if (res.schema?.fields) {
          const nameDataTypes: { [key : string] : string } = {};
          this.fieldNames = res.schema.fields!.map(x => x.name!);
          // @ts-ignore
          this.fieldDataTypes = res.schema.fields!.map(x => x.dataType.type);
          this.fieldNames.forEach((fieldName, index) => {
            nameDataTypes[fieldName] = this.fieldDataTypes[index];
          });
          this.namesDataTypes = nameDataTypes;
        }
      }, error: err => {
        console.log('Failed', err)
      }});
  }

  onClickGetTableInfoDate() {
    this.restFacadeService.getTableInfoDate().subscribe({ next: res => {
        this.filter = [];
        this.salesTableInfo = res;
        this.salesTableInfoJson = JSON.stringify(res, null, 2);
        if (res.schema?.fields) {
          const nameDataTypes: { [key : string] : string } = {};
          this.fieldNames = res.schema.fields!.map(x => x.name!);
          // @ts-ignore
          this.fieldDataTypes = res.schema.fields!.map(x => x.dataType.type);
          this.fieldNames.forEach((fieldName, index) => {
            nameDataTypes[fieldName] = this.fieldDataTypes[index];
          });
          this.namesDataTypes = nameDataTypes;
        }
      }, error: err => {
        console.log('Failed', err)
      }});
  }

  onClickGetTableInfoEvent() {
    this.restFacadeService.getTableInfoEvent().subscribe({ next: res => {
        this.filter = [];
        this.salesTableInfo = res;
        this.salesTableInfoJson = JSON.stringify(res, null, 2);
        if (res.schema?.fields) {
          const nameDataTypes: { [key : string] : string } = {};
          this.fieldNames = res.schema.fields!.map(x => x.name!);
          // @ts-ignore
          this.fieldDataTypes = res.schema.fields!.map(x => x.dataType.type);
          this.fieldNames.forEach((fieldName, index) => {
            nameDataTypes[fieldName] = this.fieldDataTypes[index];
          });
          this.namesDataTypes = nameDataTypes;
        }
      }, error: err => {
        console.log('Failed', err)
      }});
  }

  onClickGetTableInfoUser() {
    this.restFacadeService.getTableInfoUser().subscribe({ next: res => {
      this.filter = [];
        this.salesTableInfo = res;
        this.salesTableInfoJson = JSON.stringify(res, null, 2);
        if (res.schema?.fields) {
          const nameDataTypes: { [key : string] : string } = {};
          this.fieldNames = res.schema.fields!.map(x => x.name!);
          // @ts-ignore
          this.fieldDataTypes = res.schema.fields!.map(x => x.dataType.type);
          this.fieldNames.forEach((fieldName, index) => {
            nameDataTypes[fieldName] = this.fieldDataTypes[index];
          });
          this.namesDataTypes = nameDataTypes;
        }
      }, error: err => {
        console.log('Failed', err)
      }});
    console.log(this.namesDataTypes)
  }

  onClickGetTableInfoListing() {
    this.restFacadeService.getTableInfoListing().subscribe({ next: res => {
        this.filter = [];
        this.salesTableInfo = res;
        this.salesTableInfoJson = JSON.stringify(res, null, 2);
        if (res.schema?.fields) {
          const nameDataTypes: { [key : string] : string } = {};
          this.fieldNames = res.schema.fields!.map(x => x.name!);
          // @ts-ignore
          this.fieldDataTypes = res.schema.fields!.map(x => x.dataType.type);
          this.fieldNames.forEach((fieldName, index) => {
            nameDataTypes[fieldName] = this.fieldDataTypes[index];
          });
          this.namesDataTypes = nameDataTypes;
        }
      }, error: err => {
        console.log('Failed', err)
      }});
  }

  onClickGetTableInfoCategory() {
    this.restFacadeService.getTableInfoCategory().subscribe({ next: res => {
        this.filter = [];
        this.salesTableInfo = res;
        this.salesTableInfoJson = JSON.stringify(res, null, 2);
        if (res.schema?.fields) {
          const nameDataTypes: { [key : string] : string } = {};
          this.fieldNames = res.schema.fields!.map(x => x.name!);
          // @ts-ignore
          this.fieldDataTypes = res.schema.fields!.map(x => x.dataType.type);
          this.fieldNames.forEach((fieldName, index) => {
            nameDataTypes[fieldName] = this.fieldDataTypes[index];
          });
          this.namesDataTypes = nameDataTypes;
        }
      }, error: err => {
        console.log('Failed', err)
      }});
  }

  onClickGetTableInfoVenue() {
    this.restFacadeService.getTableInfoVenue().subscribe({ next: res => {
        this.filter = [];
        this.salesTableInfo = res;
        this.salesTableInfoJson = JSON.stringify(res, null, 2);
        if (res.schema?.fields) {
          const nameDataTypes: { [key : string] : string } = {};
          this.fieldNames = res.schema.fields!.map(x => x.name!);
          // @ts-ignore
          this.fieldDataTypes = res.schema.fields!.map(x => x.dataType.type);
          this.fieldNames.forEach((fieldName, index) => {
            nameDataTypes[fieldName] = this.fieldDataTypes[index];
          });
          this.namesDataTypes = nameDataTypes;
        }
      }, error: err => {
        console.log('Failed', err)
      }});
  }



  onClickQuery() {
    this.restFacadeService.queryFirstSales().subscribe({ next: res => {
      this.salesFirstRows = res;
      this.salesFirstRowsJson = JSON.stringify(res, null, 2);
    }, error: err => {
      console.log('Failed', err)
    }});
  }

  initializeDropdown(){
    this.filter = [];
    const dropdown = document.getElementById("dropdown") as HTMLSelectElement;
    if(dropdown){
      const selectedValue = dropdown.value;
      console.log(selectedValue);
      this.handleDropdownChange(selectedValue);
      this.filter.push(selectedValue);
    }
  }

  handleDropdownChange(selectedValue: string){
    /*edTable = this.dropdownValuesOptions.find(option => option.value === selectedValue)
    if(selectedTable){
      selectedTable.handler();
      console.log("Handler done")
    }*/
    switch (selectedValue) {
      case "Sales":
        this.onClickGetTableInfoSales();
        break;
      case "Date":
        this.onClickGetTableInfoDate();
        break;
      case "Event":
        this.onClickGetTableInfoEvent();
        break;
      case "User":
        this.onClickGetTableInfoUser();
        break;
      case "Listing":
        this.onClickGetTableInfoListing();
        break;
      case "Category":
        this.onClickGetTableInfoCategory();
        break;
      case "Venue":
        this.onClickGetTableInfoVenue();
        break;
    }
  }

  getFilters(value : string){
    if(this.filter.length < 1){this.filter.push(value);}
    else {while(this.filter.length >= 1){this.filter.shift();}this.filter.push(value);}
    switch(this.namesDataTypes[value]){
      case "string":
        this.filtersToReturn = ['is equal to', 'contains'];
        break;
      case "int":
        this.filtersToReturn = ['is greater than', 'is less than', 'is equal to', 'is greater or equal to', 'is less or equal to'];
        break;
      case "bool":
        this.filtersToReturn = ['is'];
        break;
      case "date":
        this.filtersToReturn = ['is greater than', 'is less than', 'is equal to', 'is greater or equal to', 'is less or equal to'];
        break;
    }
    return this.filtersToReturn;
  }

  addFilterToFilter(filter : string){
    if(this.filter.length < 2){this.filter.push(filter);}
    else{while(this.filter.length >= 2){this.filter.pop();}this.filter.push(filter);}
  }

  addValueToFilter(value : string){
    if (this.filter.length < 3){this.filter.push(value);}
    else{while(this.filter.length >= 3){this.filter.pop();}this.filter.push(value);}
    console.log(this.filter);
    this.filterList.push(this.filter);
  }

  deleteFilter(index: number){
    this.filterList.splice(index,1);
    console.log('Deleted')
  }
}
