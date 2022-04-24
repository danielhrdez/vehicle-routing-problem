package vrp.algorithm.localsearch.swap;

import vrp.algorithm.localsearch.base.LocalSearch;
import vrp.solution.Routes;

public abstract class Swap extends LocalSearch {
  protected Routes swap(Routes routes, int from, int customer1Position, int to, int customer2Position) {
    Routes newRoutes = routes.clone();
    Integer previousCustomer1 = newRoutes.getCustomer(from, customer1Position - 1);
    Integer customer1 = routes.getCustomer(from, customer1Position);
    Integer nextCustomer1 = routes.getCustomer(from, customer1Position + 1);
    Integer previousCustomer2 = routes.getCustomer(to, customer2Position - 1);
    Integer customer2 = routes.getCustomer(to, customer2Position);
    Integer nextCustomer2 = routes.getCustomer(to, customer2Position + 1);
    newRoutes.sumCost(-this.dataModel.distance(previousCustomer1, customer1));
    newRoutes.sumCost(-this.dataModel.distance(customer1, nextCustomer1));
    if (previousCustomer2 != customer1) {
      newRoutes.sumCost(-this.dataModel.distance(previousCustomer2, customer2));
    }
    newRoutes.sumCost(-this.dataModel.distance(customer2, nextCustomer2));
    newRoutes.setCustomer(from, customer1Position, customer2);
    newRoutes.setCustomer(to, customer2Position, customer1);
    newRoutes.sumCost(this.dataModel.distance(previousCustomer1, customer2));
    if (previousCustomer2 != customer1) {
      newRoutes.sumCost(this.dataModel.distance(customer2, nextCustomer1));
      newRoutes.sumCost(this.dataModel.distance(previousCustomer2, customer1));
    } else {
      newRoutes.sumCost(this.dataModel.distance(customer2, customer1));
    }
    newRoutes.sumCost(this.dataModel.distance(customer1, nextCustomer2));
    return newRoutes;
  }
}
