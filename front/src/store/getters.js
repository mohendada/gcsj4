const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  // 添加roles
  roles: state => state.user.roles,
  // 动态路由
  permission_routes: state => state.permission.routes
}
export default getters
