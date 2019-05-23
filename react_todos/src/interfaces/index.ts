import { ComponentProps } from 'react'


//===========================entity==================//
export interface ITodo {
  id: number;
  content: string;
  createdTime: Date;
  done: boolean;
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
export interface IBaseProps extends ComponentProps<any> {
}

export interface IPageProps extends IBaseProps {

}

export interface IAppProps extends IPageProps {
  common: ICommonStore;
}

export interface ITodoPageProps extends IPageProps {
  common: ICommonStore;
  todo: ITodoStore;
}

export interface ITodoProps extends ITodo, IBaseProps {

}
export interface ITodoPropsWithStore extends ITodoProps {
  todo: ITodoStore;
}

export interface ITodoModalProps extends IBaseProps {
  visible: boolean;
  onSave: ((e: React.MouseEvent<any, MouseEvent>) => void);
  onCancel: ((e: React.MouseEvent<any, MouseEvent>) => void);
}

export interface ITodoModalPropsWithStore extends ITodoModalProps {
  todo: ITodoStore;
}


export interface ITodoListProps extends IBaseProps {
  todos: (ITodo)[];
  title: string;
  canAdd: boolean;
}

//------------state----------//


//-----------render----------//

