export enum Direction {
  ASC = 'ASC', DESC = 'DESC'
}

export enum Logic {
  AND = 'and', OR = 'or'
}

export enum Operator {
  EQUAL = 'eq', NOT_EQUAL = 'neq', IS_NULL = 'isnull', IS_NOT_NULL = 'isnotnull', IS_EMPTY = 'isempty',
  IS_NOT_EMPTY = 'isnotempty', CONTAINS = 'contains', NOT_CONTAINS = 'notcontains', STARTS_WITH = 'startswith',
  ENDS_WITH = 'endswith', GREATER_THAN = 'gt', LESS_THAN = 'lt', GREATER_THAN_OR_EQUAL = 'gte',
  LESS_THAN_OR_EQUAL = 'lte', IN = 'in'
}

export class SortFilter {
  field: string;
  direction: Direction;

  constructor(field: string, dir?: string) {
    this.field = field;
    if (dir) {
      this.direction = Direction[dir.toUpperCase()];
    }
  }
}

export abstract class AbstractPageFilter {
  pageNumber: number;
  pageSize: number;
  order: any;
}

export class AnyPageFilter extends AbstractPageFilter {
  value: string;
  fields: Array<AnyField>;
  ignoreCase: boolean;

  constructor(value: string, fields: Array<AnyField>, pageNumber: number, pageSize: number, sortColumn?: string) {
    super();
    this.value = value;
    this.fields = fields;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.order = sortColumn;
  }
}

export class LangAnyPageFilter extends AnyPageFilter {
  lang: string;

  constructor(value: string, fields: Array<AnyField>, pageNumber: number, pageSize: number, sortColumn: string, lang: string) {
    super(value, fields, pageNumber, pageSize, sortColumn);
    this.lang = lang;
  }
}

export class LangAnyPageFilters extends LangAnyPageFilter {
  filters: Array<Filter>;
}

export class AnyField {
  field: string;
  operator?: Operator;

  constructor(field: string, operator?: Operator) {
    this.field = field;
    this.operator = operator;
  }
}

export class Filter {
  field: string;
  operator: Operator;
  value: any;
  logic: Logic;
  ignoreCase: boolean;
  filters: Array<Filter>;

  constructor(field: string, operator: Operator, value: any, ignoreCase?: boolean, logic?: Logic, ...filters: Filter[]) {
    this.field = field;
    this.operator = operator;
    this.value = value;
    this.logic = logic;
    this.ignoreCase = ignoreCase;
    this.filters = filters;
  }
}
