package com.river.design.StatePattern.state;

import com.river.design.StatePattern.model.Context;

public interface State {
   public void doAction(Context context);
}