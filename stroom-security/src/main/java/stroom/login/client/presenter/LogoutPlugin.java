/*
 * Copyright 2016 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package stroom.login.client.presenter;

import com.google.gwt.user.client.Command;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import stroom.alert.client.event.ConfirmEvent;
import stroom.alert.client.presenter.ConfirmCallback;
import stroom.app.client.MenuKeys;
import stroom.app.client.presenter.Plugin;
import stroom.menubar.client.event.BeforeRevealMenubarEvent;
import stroom.security.client.event.RequestLogoutEvent;
import stroom.widget.button.client.GlyphIcons;
import stroom.widget.menu.client.presenter.IconMenuItem;
import stroom.widget.menu.client.presenter.KeyedParentMenuItem;
import stroom.widget.menu.client.presenter.Separator;

public class LogoutPlugin extends Plugin {
    @Inject
    public LogoutPlugin(final EventBus eventBus) {
        super(eventBus);
    }

    @Override
    public void onReveal(final BeforeRevealMenubarEvent event) {
        super.onReveal(event);

        event.getMenuItems().addMenuItem(MenuKeys.MAIN_MENU,
                new KeyedParentMenuItem(4, "User", event.getMenuItems(), MenuKeys.USER_MENU));
        event.getMenuItems().addMenuItem(MenuKeys.USER_MENU, new Separator(2));
        event.getMenuItems().addMenuItem(MenuKeys.USER_MENU,
                new IconMenuItem(3, GlyphIcons.LOGOUT, GlyphIcons.LOGOUT, "Logout", null, true, new Command() {
                    @Override
                    public void execute() {
                        ConfirmEvent.fire(LogoutPlugin.this, "Are you sure you want to logout?", new ConfirmCallback() {
                            @Override
                            public void onResult(final boolean result) {
                                if (result) {
                                    RequestLogoutEvent.fire(LogoutPlugin.this);
                                }
                            }
                        });
                    }
                }));
    }
}
