
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
  unfinishedTodos: (ITodo)[];
  doneTodos: (ITodo)[];
  addNewTodo: Function;
  removeDoneTodo: Function;
  clearDone: Function;
  addAll: Function;
  moveToDone: Function;
  doneAll: Function;
  delNewTodo: Function;
}


//================================================component============================================//
//------------props----------//
export interface IBaseProps {
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

export interface ITodoListProps extends IBaseProps {
  todos: (ITodo)[];
  title: string;
}

//------------state----------//


//-----------render----------//

