import { createBrowserHistory } from "history";


/**
 * История нужна для корректных редиректов
 */
export default createBrowserHistory({forceRefresh:true});