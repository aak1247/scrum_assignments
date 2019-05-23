
//===========================entity==================//
export interface ITodo {
  id: number;
  content: string;
  createdTime: Date;
}

//==========================store======================//
export interface ICommonStore {
  locale: string;
  changeLanguage: Function;
}

export interface ITodoStore {
  todos: (ITodo)[];
  addTodo: Function;
  removeTodo: Function;
  clear: Function;
  addAll: Function;
}


//================================================component============================================//
//------------props----------//
export interface IBaseProps {
  common?:ICommonStore;
  todo?:ITodoStore;
}

export interface IPageProps extends IBaseProps{
  
}

export interface IAppProps extends IPageProps {
  common: ICommonStore;
}

export interface ITodoPageProps extends IPageProps{
  common: ICommonStore;
  todo: ITodoStore;
}

export interface ITodoProps extends ITodo {

}

//------------state----------//


//-----------render----------//

