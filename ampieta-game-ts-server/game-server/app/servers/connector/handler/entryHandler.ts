import { Application, FrontendSession } from 'pinus';

export default function(app: Application) {
  return new Handler(app);
}

export class Handler {
  constructor(private app: Application) {
    let protoclData = {
      id: 1,
      data: { code: 500, msg: "消息成功" }
    }

  }

  /**
   * New client entry.
   *
   * @param  {Object}   msg     request message
   * @param  {Object}   session current session object
   */
  async entry(msg: any, session: FrontendSession) {
    let data = {
      code: 500,
      msg: "消息成功了"
    }

    return data;
  }

  /**
   * Publish route for mqtt connector.
   *
   * @param  {Object}   msg     request message
   * @param  {Object}   session current session object
   */
  async publish(msg: any, session: FrontendSession) {
    let result = {
      topic: 'publish',
      payload: JSON.stringify({ code: 200, msg: 'publish message is ok.' })
    };
    return result;
  }

  /**
   * Subscribe route for mqtt connector.
   *
   * @param  {Object}   msg     request message
   * @param  {Object}   session current session object
   */
  async subscribe(msg: any, session: FrontendSession) {
    let result = {
      topic: 'subscribe',
      payload: JSON.stringify({ code: 200, msg: 'subscribe message is ok.' })
    };
    return result;
  }

}
