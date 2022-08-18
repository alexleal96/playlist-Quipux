import { useRouter } from 'vue-router'
import { expect, test, vi } from "vitest"
import { mount } from '@vue/test-utils'
import HelloWorld from '../components/HelloWorld.vue'

vi.mock('vue-router', () => ({
    useRouter: vi.fn(() => ({
        push: () => { }
    }))
}));

test('Prueba piloto router', async () => {

    const push = vi.fn();
    // @ts-ignore
    useRouter.mockImplementationOnce(() => ({
        push
    }));

    const wrapper = mount(HelloWorld);
    await wrapper.find("[data-test='button']").trigger('click');
    expect(push).toHaveBeenCalled();
});